/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.ideation;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.extend.modules.comment.business.Comment;
import fr.paris.lutece.plugins.extend.modules.comment.business.CommentFilter;
import fr.paris.lutece.plugins.extend.modules.comment.business.ICommentDAO;
import fr.paris.lutece.plugins.extend.modules.comment.service.CommentPlugin;
import fr.paris.lutece.plugins.extend.modules.comment.service.CommentService;
import fr.paris.lutece.plugins.extend.modules.comment.service.ICommentService;
import fr.paris.lutece.plugins.participatorybudget.service.IMyInfosListener;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.Proposal;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.ProposalHome;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.ProposalSearcher;
import fr.paris.lutece.plugins.participatoryideation.service.SolrProposalIndexer;
import fr.paris.lutece.plugins.participatoryideation.service.subscription.IdeationSubscriptionProviderService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;

public class IdeationMyInfosListener implements IMyInfosListener
{

    private static final String BEAN_SOLR_PROPOSAL_INDEXER = "participatoryideation.solrProposalIndexer";
    private static final String BEAN_COMMENT_DAO = "extend-comment.commentDAO";

    private ICommentService _commentService = SpringContextService.getBean( CommentService.BEAN_SERVICE );
    private SolrProposalIndexer _solrProposalIndexer = SpringContextService.getBean( BEAN_SOLR_PROPOSAL_INDEXER );
    private ICommentDAO _commentDAO = SpringContextService.getBean( BEAN_COMMENT_DAO );

    @Override
    public void updateNickName( String strLuteceUserName, String strNickName )
    {
        // update comments
        CommentFilter commentFilter = new CommentFilter( );
        commentFilter.setLuteceUserName( strLuteceUserName );

        List<Comment> listComments = _commentService.findByResource( "*", Proposal.PROPERTY_RESOURCE_TYPE, commentFilter, 0, 10000, false );

        if ( listComments != null )
        {

            for ( Comment comment : listComments )
            {
                Comment commentPrimary = _commentService.findByPrimaryKey( comment.getIdComment( ) );
                commentPrimary.setName( strNickName );
                _commentDAO.store( commentPrimary, CommentPlugin.getPlugin( ) );
            }

        }
        // reindex all user proposals

        ProposalSearcher proposalSearcher = new ProposalSearcher( );
        proposalSearcher.setLuteceUserName( strLuteceUserName );
        proposalSearcher.setIsPublished( true );

        Collection<Proposal> proposalsSubmitted = ProposalHome.getProposalsListSearch( proposalSearcher );
        for ( Proposal proposal : proposalsSubmitted )
        {

            _solrProposalIndexer.writeProposal( proposal );

        }
    }

    @Override
    public void createNickName( String strLuteceUserName, String strNickName )
    {
        ReferenceList refList = IdeationSubscriptionProviderService.getService( ).getRefListIdeationSubscription( Locale.FRENCH );

        for ( ReferenceItem refItem : refList )
        {
            IdeationSubscriptionProviderService.getService( ).createSubscription( strLuteceUserName, refItem.getCode( ) );

        }
    }

    @Override
    public int canChangeArrond( LuteceUser user )
    {
        // return default value
        return 0;
    }

    @Override
    public String deleteVotes( HttpServletRequest request )
    {
        return null;
    }

}
